package by.bsuir.glebsorkin.wt.tasks.third.server.command.impl;

import by.bsuir.glebsorkin.wt.tasks.third.server.command.Command;
import by.bsuir.glebsorkin.wt.tasks.third.server.command.exception.CommandException;
import by.bsuir.glebsorkin.wt.tasks.third.server.model.AuthType;
import by.bsuir.glebsorkin.wt.tasks.third.server.model.Case;
import by.bsuir.glebsorkin.wt.tasks.third.server.service.ServiceFactory;

import java.util.List;

public class ViewCommand implements Command {
    public String complete(Object caller, String request) throws CommandException {
        if (ServiceFactory.getInstance().getAuthService().getAuthType(caller) == AuthType.UNAUTH)
            return "Should be authenticated";

        List<Case> cases = ServiceFactory.getInstance().getCaseService().getAll();
        return toOutput(cases);
    }

    private static String toOutput(List<Case> cases) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("[\n");
        for (var box : cases) {
            resultBuilder.append("\t").append(box.toString()).append("\n");
        }
        resultBuilder.append("]");
        return resultBuilder.toString();
    }
}
