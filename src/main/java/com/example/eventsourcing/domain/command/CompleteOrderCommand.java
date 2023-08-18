package com.example.eventsourcing.domain.command;

import com.example.eventsourcing.domain.AggregateType;

import java.util.UUID;

public final class CompleteOrderCommand extends Command {

    public CompleteOrderCommand(UUID aggregateId) {
        super(AggregateType.ORDER, aggregateId);
    }

    @Override
    public String toString() {
        return "CompleteOrderCommand(super=" + super.toString() + ")";
    }
}