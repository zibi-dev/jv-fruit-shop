package core.basesyntax.strategy.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationHandler;

public class PurchaseOperation implements OperationHandler {
    @Override
    public void handle(FruitTransaction transaction) {
        int current = Storage.fruits.getOrDefault(transaction.getFruit(), 0);
        int remaining = current - transaction.getQuantity();

        if (remaining < 0) {
            throw new RuntimeException(
                    "Not enough "
                            + transaction.getFruit()
                            + " in stock. Available: "
                            + current
                            + ", requested: "
                            + transaction.getQuantity()
            );
        }
        Storage.fruits.put(transaction.getFruit(), remaining);
    }
}
