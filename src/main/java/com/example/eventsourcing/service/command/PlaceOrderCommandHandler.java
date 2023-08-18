package com.example.eventsourcing.service.command;

import com.example.eventsourcing.domain.Aggregate;
import com.example.eventsourcing.domain.command.Command;
import com.example.eventsourcing.domain.command.PlaceOrderCommand;
import org.springframework.stereotype.Component;

@Component
public class PlaceOrderCommandHandler implements CommandHandler<PlaceOrderCommand> {

    @Override
    public void handle(Aggregate aggregate, Command command) {
        // Add additional business logic here.
        aggregate.process(command);
        // Also, add additional business logic here.
        // Read other aggregates using AggregateStore.
    }

    @Override
    public Class<PlaceOrderCommand> getCommandType() {
        return PlaceOrderCommand.class;
    }
}
