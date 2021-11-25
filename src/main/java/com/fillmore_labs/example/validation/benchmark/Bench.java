package com.fillmore_labs.example.validation.benchmark;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;

import com.fillmore_labs.example.validation.self_validating.Person;
import com.fillmore_labs.example.validation.validated.PersonFactory;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(AverageTime)
@OutputTimeUnit(NANOSECONDS)
@Fork(1)
@Warmup(iterations = 6, time = 3)
@Measurement(iterations = 5, time = 5)
public /* open */ class Bench {
  @Benchmark
  public final Person selfValidating() {
    return new Person("Kartini", null);
  }

  @Benchmark
  @RequiresNonNull({"#1.factory"})
  public final com.fillmore_labs.example.validation.validated.Person validated(MyState myState) {
    return myState.factory.create("Kartini", null);
  }

  @Benchmark
  public final com.fillmore_labs.example.validation.immutables.Person immutables() {
    return com.fillmore_labs.example.validation.immutables.Person.builder()
        .givenName("Kartini")
        .build();
  }

  @Benchmark
  public final com.fillmore_labs.example.validation.pojo.Person pojo() {
    return new com.fillmore_labs.example.validation.pojo.Person("Kartini", null);
  }

  @State(Scope.Benchmark)
  public static /* open */ class MyState {
    public @MonotonicNonNull PersonFactory factory;
    private @MonotonicNonNull ValidatorFactory validatorFactory;

    @EnsuresNonNull({"validatorFactory", "factory"})
    @Setup
    public final void setUp() {
      validatorFactory = Validation.buildDefaultValidatorFactory();
      factory = new PersonFactory(this.validatorFactory.getValidator());
    }

    @TearDown(Level.Trial)
    @RequiresNonNull({"validatorFactory"})
    public final void tearDown() {
      validatorFactory.close();
    }
  }
}
