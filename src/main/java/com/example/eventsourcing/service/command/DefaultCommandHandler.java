package com.example.eventsourcing.service.command;

import com.example.eventsourcing.domain.Aggregate;
import com.example.eventsourcing.domain.command.Command;
import org.springframework.stereotype.Component;

@Component
public class DefaultCommandHandler implements CommandHandler<Command> {

    @Override
    public void handle(Aggregate aggregate, Command command) {
        aggregate.process(command);
    }

    @Override
    public Class<Command> getCommandType() {
        return Command.class;
    }
}
