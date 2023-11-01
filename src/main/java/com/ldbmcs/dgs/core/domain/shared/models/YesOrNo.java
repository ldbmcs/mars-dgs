package com.ldbmcs.dgs.core.domain.shared.models;

import com.baomidou.mybatisplus.annotation.IEnum;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public enum YesOrNo implements IEnum<String> {
    YES,
    NO;

    public boolean isYes() {
        return this == YES;
    }

    public boolean isNo() {
        return this == NO;
    }

    public YesOrNo or(@NotNull YesOrNo value) {
        Objects.requireNonNull(value, "value can not be null.");
        return or(value.isYes());
    }

    public YesOrNo or(boolean value) {
        return fromBoolean(this.isYes() || value);
    }

    public YesOrNo and(@NotNull YesOrNo value) {
        Objects.requireNonNull(value, "value can not be null.");
        return and(value.isYes());
    }

    public YesOrNo and(boolean value) {
        return fromBoolean(this.isYes() && value);
    }

    public static YesOrNo fromBoolean(boolean value) {
        return value ? YES : NO;
    }

    public static YesOrNo fromString(String value) {
        return YES.name().equalsIgnoreCase(value) ? YES : NO.name().equalsIgnoreCase(value) ? NO : null;
    }

    public void ifYes(Consumer<YesOrNo> consumer) {
        if (this.isYes()) {
            consumer.accept(this);
        }
    }

    public void ifYes(EmptyConsumer emptyConsumer) {
        if (this.isYes()) {
            emptyConsumer.accept();
        }
    }

    public void ifYesOr(Consumer<YesOrNo> yesConsumer, Consumer<YesOrNo> noConsumer) {
        if (this.isYes()) {
            yesConsumer.accept(this);
        } else {
            noConsumer.accept(this);
        }
    }

    public void ifYesOr(EmptyConsumer yesConsumer, EmptyConsumer noConsumer) {
        if (this.isYes()) {
            yesConsumer.accept();
        } else {
            noConsumer.accept();
        }
    }

    public <T> T ifYesSupplierOr(Supplier<T> yesSupplier, Supplier<T> noSupplier) {
        if (this.isYes()) {
            return yesSupplier.get();
        } else {
            return noSupplier.get();
        }
    }

    public <T> T ifYes(Supplier<T> yesSupplier, T defaultValue) {
        if (this.isYes()) {
            return yesSupplier.get();
        } else {
            return defaultValue;
        }
    }

    public <T> T ifNo(Supplier<T> noSupplier, T defaultValue) {
        if (this.isNo()) {
            return noSupplier.get();
        } else {
            return defaultValue;
        }
    }

    public <R> Optional<R> ifYes(Supplier<R> supplier) {
        return Optional.of(this)
                .filter(YesOrNo::isYes)
                .map(v -> supplier.get());
    }

    public <R> R yesOrNull(Supplier<R> supplier) {
        return ifYes(supplier).orElse(null);
    }

    public void ifNo(Consumer<YesOrNo> consumer) {
        if (this.isNo()) {
            consumer.accept(this);
        }
    }

    public void ifNo(EmptyConsumer emptyConsumer) {
        if (this.isNo()) {
            emptyConsumer.accept();
        }
    }

    public <R> Optional<R> ifNo(Supplier<R> supplier) {
        return Optional.of(this)
                .filter(YesOrNo::isNo)
                .map(v -> supplier.get());
    }

    public <X extends Throwable> void ifNoThrow(Supplier<? extends X> exceptionSupplier) throws X {
        Optional.of(this).filter(YesOrNo::isYes)
                .orElseThrow(exceptionSupplier);
    }

    public <X extends Throwable> void ifYesThrow(Supplier<? extends X> exceptionSupplier) throws X {
        Optional.of(this).filter(YesOrNo::isNo)
                .orElseThrow(exceptionSupplier);
    }

    public YesOrNo ifYesApply(EmptyConsumer consumer) {
        if (this.isYes()) {
            consumer.accept();
        }
        return this;
    }

    public <R> R noOrNull(Supplier<R> supplier) {
        return ifNo(supplier).orElse(null);
    }

    public <R> R map(Supplier<R> yesSupplier, Supplier<R> noSupplier) {
        if (isYes()) {
            return yesSupplier.get();
        }
        return noSupplier.get();
    }

    @Override
    public String getValue() {
        return name();
    }
}
