# JavaSealedUnions

## ACKNOWLEDGEMENTS
This library was heavily inspired by [RxEither](https://github.com/eleventigers/rxeither) and the wonderful [Domain Driven Design](https://fsharpforfunandprofit.com/ddd/) (DDD) talk by [Scott Wlaschin](https://github.com/swlaschin). Another similar talk with the full [Tennis kata](http://www.codingdojo.org/cgi-bin/index.pl?KataTennis) we'll use as an example below is [Types + Properties = Software](https://channel9.msdn.com/Events/FSharp-Events/fsharpConf-2016/Types-Properties-Software) by [Mark Seemann](https://github.com/ploeh).

## DISTRIBUTION
Add as a dependency to your `build.gradle`

    repositories {
        ...
        maven { url "https://jitpack.io" }
        ...
    }

    dependencies {
        ...
        compile 'com.github.pakoito:JavaSealedUnions:1.0.0'
        ...
    }

or to your `pom.xml`

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

    <dependency>
        <groupId>com.github.pakoito</groupId>
        <artifactId>JavaSealedUnions</artifactId>
        <version>1.0.0</version>
    </dependency>

## RATIONALE
JavaSealedUnions brings unions into idiomatic Java 8 to allow for better domain modelling. It can also help representing sealed classes, but that is not the main focus. Chaining operations and monadic composition using JavaSealedUnions is also outside the scope of the library, but any union can be lifted to other frameworks like [RxJava](http://reactivex.io/) and [Javaslang](http://www.javaslang.io/). A backport library for RxJava and Java 6 is available at [pakoito/RxSealedUnions](https://github.com/pakoito/RxSealedUnions).

Java's type system is considered not very powerful although it contains most OOP niceties. Some of the most known absences are [tagged unions](https://en.wikipedia.org/wiki/Tagged_union) and sealed classes. Sealed classes are available in languages like [Kotlin](https://kotlinlang.org/docs/reference/classes.html#sealed-classes), or [C#](https://msdn.microsoft.com/en-gb/library/88c54tsw.aspx). Tagged unions are common on [Swift](https://developer.apple.com/library/ios/documentation/Swift/Conceptual/Swift_Programming_Language/Enumerations.html) and [Rust](https://doc.rust-lang.org/book/enums.html).

The most frequent approach to solve this modelling problem in Java is having a base class or interface `IMyContract` and implementing several of `IChildContractPlusExtras` for public scopes. Another alternative is having a public `abstract` class that is inherited by a small set of package-only classes. The problem with the first approach is the possibility of breaking encapsulation and being able to implement the interface by a 3rd party outside the desired outcomes. The second approach hides the implementations for you, which requires the use of runtime tools like `instanceof` to handle. Both cases have one common problem: they only allow association of classes that are of the same type, or in the same hierarchy.

The intent of this library is to create a set of classes that can host one element of one to several arbitrary complex types. Historically other libraries like Guava or RxJava have provided chainable types to solve this issue:

* The base case is `Result<T>` (also known as `Optional`), which is the union of two types: `Some<T>` and `None`.

* The next case is `Either<A, B>` or `Try<T, Exception>`, which respectively wrap the union of two arbitrary types `Left<L>` and `Right<R>`, or `Success<T>` and `Failure<Exception>`.

For a higher number of parameters no abstraction is usually provided, and it's when other languages change to explicit union types. As Java does not have unions on the language, we have to continue abstracting away with 3 types (Left<L>, Middle<M> and Right<R>), 4 types, 5 types...etc.

We're calling them `Union1<T>` for `Result`/`Optional`, `Union2<L, R>` for `Either`/`Try`, `Union3<L, M, R>`...up to `UnionN`, which for this library would be `Union9<A, B, C, D, E, F, G, H, I>`.

I heavily recommend watching the DDD talk linked above first to see what solutions this library provides compared to chainable types. Unions are used for intra-layer modelling, chainables are more fit for inter-layer communication.

## INTERFACES
Now that we understand what the abstraction has to provide, we have to define a public API:

*What defines a union?*

It's a type that allows storage of a single element that can be from any of 2-N different types. It's container and implementation agnostic. The only requirement is to have ways to retrieve the data inside. To be properly composable it requires using interface composition rather than abstract inheritance.

*What belongs in the interface?*

It needs to be able to dereference the types to obtain a single, unequivocal, result. It should avoid extra operations, not throw exceptions, and not be able to represent error states.

*How is this done in other languages or frameworks?*

- Nested ifs:

```
if (union.isOne()) {
    One element = union.getOne();
    /* do something with one*/ 
} else if (union.isTwo()) {
    Two element = union.getTwo();
    /* do something with two*/ 
} else...
```

 ... and so and so until 9. Problem? The api can be dereferenced using `getXXX()` without calling any of the `isXXX()` methods, leading to exceptions and unexpected states. It adds one unneeded extra operation.

- Polymorphism:

```
MyElement element = createElement();
if (element instanceof One) {
    One one = (One)element;
    /* do something with one*/
} else if (element instanceof Two) {
    Two two = (Two)element;
    /* do something with two*/
} else...
```
It suffers from the same shortcomings as nested ifs: it requires programmer discipline to remember and check before casting, plus it leans to the same errors. The only change is that it now requires two operations: `instanceof` and a cast.

- Pattern matching: not available in Java. But the intent of a pattern matcher is double: either continue to another piece of code, or return a single result element. This ties directly to the next two points.

- Continuations: part of [Inversion of Control](https://en.wikipedia.org/wiki/Inversion_of_control), it's a principle where a function decides what to do with the execution flow after it's done. In this case you provide one method per type in the union indicating how the operation has to continue after dereferencing. It branches execution synchronously into the continuations without having to check types externally. It does not allow representing incorrect states, dereferencing unavailable types, or any other causes of Exceptions save for NPEs.

- Joining: provide a function per element in the union that maps them back into a single common type that the current execution flow knows how to use. The mapping is applied synchronously and the flow continues on the same method. The caller is responsible of assuring the mapping operation is correct, or have a fallback case.

For the library I have chosen continuations and joining as the default methods in the interface.

**NOTE: you should never ever require or implement `getXXX()` as a way of returning a type inside the union. It defeats the purpose of the library. Direct dereference is error-prone, tends to be abused by programmers, and has been cited as a mistake when creating `Optional`-like libraries.**

### Final implementation of Union2

```
public interface Union2<Left, Right> {

    void continued(Consumer<First> continuationFirst, Consumer<Second> continuationSecond);

    <R> R join(Function<First, R> mapFirst, Function<Second, R> mapSecond);
}
```

And one example usage:

```
Union2<User, Team> information = serverRequester.loggedAccountInformation();

// Get a single piece of information from either
List<Purchase> totalPurchases = information.join(user -> user.getPurchases(), team -> team.getAccountManager().getPurchases());

// Or you can finish the current method and continue somewhere else depending on the type
information.continued(UserPageTemplater::start(), TeamPageTemplater::start());
```

### Creation
Part of creating a union is that the union itself is a new type and has to be represented too. For this case it's been included one Factory interface per UnionN that can be extended and required to create each one of the elements in the union:

```
public interface Factory<Left, Right> {

    Union2<Left, Right> first(Left first);

    Union2<Left, Right> second(Right second);

}
```

## USAGE

### Generic unions
This set of classes are provided by the library to wrap any class regardless of its type. They come in flavours from `Union1` to `Union9`. `GenericUnions` is a class with factories for all the union types. Factories can be provided by calling one of `singletFactory()`, `doubletFactory()`, `tripletFactory()`, `quartetFactory()`, `quintetFactory()`, `sextetFactory()`, `septetFactory()`, `octetFactory()` and `nonetFactory()`.
```
public class LoggedInAccount {
    public final String id;

    public long timestamp;

    public final Union4<User, Group, Administrator, Guest> account;

    public LoggedInAccount(String id, long timestamp, Union4<User, Group, Administrator, Guest> account){
        this.id = id;
        this.timestamp = timestamp;
        this.account = account;
    }
}

public LoggedInAccount login(String id){
    Union4.Factory<User, Group, Administrator, Guest> quartetFactory = GenericUnions.quartetFactory();
    Union4<User, Group, Administrator, Guest> user =
                                                database.getAccount(id)
                                                    ? quartetFactory.third(database.requestAdmin(id)
                                                    : quartetFactory.first(database.requestUser(id))
    LoggedInAccount account = new LoggedInAcctount(id, System.currentTimeMillis(), user);
    return account;
}
```

### Typed wrappers
In case you want your unions to be driven by your domain you have to create your own classes implementing the base interfaces. There are several recommended approaches:

#### Holder class with generic union
A domain class giving a more explicit naming and access to its methods and content.

**REMINDER: Implement `getXXX()` as a way of returning a type inside the union defeats the purpose of unions.**

```
public class Salute {

    private static final Either.Factory<Dog, Neighbour> FACTORY = GenericUnions.eitherFactory();

    public static Salute dog(String name, int paws) {
        return new Salute(FACTORY.left(new Dog(name, paws)));
    }

    public static Salute neighbour(String name, String favouriteFood, boolean isLiked) {
        return new Salute(FACTORY.right(new Neighbour(name, favouriteFood, isLiked)));
    }

    private final Union2<Dog, Neighbour> either;

    Salute(Union2<Dog, Neighbour> either) {
        this.either = either;
    }

    public void openDoor(Consumer<Dog> continueDog, Consumer<Neighbour> continueNeighbour) {
        return either.continued(continueDog, continueNeighbour);
    }

    public String rememberSalute(Function<Dog, String> mapDog, Function<Neighbour, String> mapNeighbour) {
        return either.join(mapDog, mapNeighbour);
    }
}


// Example
String salute = getSalute().rememberSalute(dog -> "Who's a good dog?", neighbour-> neighbour.isLiked? "Good morning, " + neighbour.name + "!" : "*grunt*");
getSalute().openDoor(dogSaluter::salute(), neighbourSaluter::salute());
```

#### Subclassing
This ties up to the inheritance approach, except it's sealed and explicit. It can be done by both abstract classes or interfaces.

**As a personal rule I would avoid any inherited methods, overloading, or overriding in any of the child classes. Watch the DDD talk in the Acknowledgements section to better understand the use of union types as plain data.**

**The example below breaks this rule by adding a new method `valid()`.**
```
public abstract class PaymentType implements Union3<CardPayment, PayPalPayment, BankTransferPayment> {

    public abstract boolean valid();

    public static PaymentType card(String cardNo, String ccv) {
        return new CardPayment(cardNo, ccv);
    }

    public static PaymentType paypal(String paypalNo, String password) {
        return new PayPalPayment(paypalNo, password);
    }

    public static PaymentType bankTransfer(String accNo) {
        return new BankTransferPayment(accNo);
    }
}

class CardPayment extends PaymentType {

    private final String cardNo;

    private final String ccv;

    CardPayment(String cardNo, String ccv) {
        this.cardNo = cardNo;
        this.ccv = ccv;
    }
    
    public boolean valid() {
        return /* some logic here */
    }

    public void continued(Consumer<CardPayment> continuationLeft, Consumer<PayPalPayment> continuationMiddle, Consumer<BankTransferPayment> continuationRight) {
        continuationLeft.call(value);
    }

    public <T> T join(Function<CardPayment, T> mapLeft, Function<PayPalPayment, T> mapMiddle, Function<BankTransferPayment, T> mapRight) {
        return mapLeft.call(value);
    }
}

class PayPalPayment extends PaymentType {

    private final String user;

    private final String pwd;

    CardPayment(String user, String pwd) {
        this.user = user;
        this.pwd = pwd;
    }
    
    public boolean valid() {
        return /* some logic here */
    }

    public void continued(Consumer<CardPayment> continuationLeft, Consumer<PayPalPayment> continuationMiddle, Consumer<BankTransferPayment> continuationRight) {
        continuationMiddle.call(value);
    }

    public <T> T join(Function<CardPayment, T> mapLeft, Function<PayPalPayment, T> mapMiddle, Function<BankTransferPayment, T> mapRight) {
        return mapMiddle.call(value);
    }
}


class BankTransferPayment extends PaymentType {

    private final String accountNo;

    CardPayment(String accountNo){
        this.accountNo = accountNo;
    }
    
    public boolean valid() {
        return /* some logic here */
    }

    public void continued(Consumer<CardPayment> continuationLeft, Consumer<PayPalPayment> continuationMiddle, Consumer<BankTransferPayment> continuationRight) {
        continuationRight.call(value);
    }

    public <T> T join(Function<CardPayment, T> mapLeft, Function<PayPalPayment, T> mapMiddle, Function<BankTransferPayment, T> mapRight) {
        return mapRight.call(value);
    }
}

// Example

PaymentType payment = getUserPayment();
if (payment.valid()) {
    payment.continued(paymentService::byCard(), paymentService::withPayPal(), paymentService::viaBankTransfer())
}
```

#### DDD
The last approach is the recommended to make the most out of the principles described across this document, using types rather than inheritance or fields.

A complete version of the [Tennis kata](http://www.codingdojo.org/cgi-bin/index.pl?KataTennis) can be found in [TennisGame.java](sealedunions/src/test/java/com/pacoworks/sealedunions/TennisGame.java) along with usage tests at [TennisGameTest.java](sealedunions/src/test/java/com/pacoworks/sealedunions/TennisGameTest.java)
```
public interface Score {

    Union4<Points, Advantage, Deuce, Game> getScore();
}

public final class Points extends Pair<PlayerPoints, PlayerPoints> { }

public interface PlayerPoints {

    Union4<Zero, Fifteen, Thirty, Forty> getPlayerPoints();
}

public interface Advantage extends Player { }

public final class Deuce { }

public interface Game extends Player { }

public interface Player {

    Union2<PlayerOne, PlayerTwo> getPlayer();
}
```

# License

Copyright (c) pakoito 2016

The Apache Software License, Version 2.0

See LICENSE.md
