package Managers;

import Workers.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import iEatWhatEvents.Event;
import iEatWhatEvents.Response;

import java.io.IOException;
import java.util.HashMap;

public class UserManager {
    private static ObjectMapper om = new ObjectMapper();

    public static Response UserEvent(Event evt) throws IOException {
        HashMap payload = om.readValue(evt.getPayload(), HashMap.class);
        String type = evt.getType().split(":")[1];
        switch (type) {
            case "NEW_USER":
                String newUserResult = Users.newUser(payload);
                if (newUserResult != null) {
                    return new Response(
                        true,
                        "User Created",
                        newUserResult,
                        evt.getType()
                    );
                }
                return new Response(
                        false,
                        "User could not be created.",
                        "No Body",
                        evt.getType()
                );
            case "UPDATE_USER":
                int updateUserResult = Users.updateUser(payload);
                if (updateUserResult == 1) {
                    return new Response(
                        true,
                        "User Updated",
                        1,
                        evt.getType());
                }
                return new Response(
                        false,
                        "User could not be updated.",
                        "No Body",
                        evt.getType()
                );
            case "GET_USER":
                return new Response(
                    true,
                    "User Updated",
                    Users.getUser(payload),
                    evt.getType());
            case "REMOVE_USER":
                int removeUserResult = Users.removeUser(payload);
                if (removeUserResult == 1) {
                    return new Response(
                            true,
                            "User Removed",
                            removeUserResult,
                            evt.getType()
                    );
                }
                return new Response(
                        false,
                        "Could not remove User.",
                        removeUserResult,
                        evt.getType()
                );
            case "NEW_REVIEW":
                Object newReviewResult = Users.newReview(payload);
                if (newReviewResult != null) {
                    return new Response(
                        true,
                        "Review Created",
                        newReviewResult,
                        evt.getType()
                    );
                }
                return new Response(
                        false,
                        "Review could not be created.",
                        "No Body",
                        evt.getType()
                );
            case "UPDATE_REVIEW":
                Object updateReviewResult = Users.updateReview(payload);
                return new Response(
                        true,
                        "Review Updated",
                        updateReviewResult,
                        evt.getType()
                );
            case "REMOVE_REVIEW":
                int removeReviewResult = Users.removeReview(payload);
                if (removeReviewResult != -1) {
                    return new Response(
                        true,
                        "Review Created",
                        removeReviewResult,
                        evt.getType()
                    );
                }
                return new Response(
                    false,
                    "Review could not be created.",
                    "No Body",
                    evt.getType()
                );
            case "ADD_CATEGORY":
                int addCategoryResult = Users.addCategory(payload);
                if (addCategoryResult == 1) {
                    return new Response(
                            true,
                            "Category added.",
                            addCategoryResult,
                            evt.getType()
                    );
                }
                return new Response(
                        false,
                        "Category could not be added.",
                        "No Body",
                        evt.getType()
                );
            case "REMOVE_CATEGORY":
                int removeCategoryResult = Users.removeCategory(payload);
                if (removeCategoryResult == 1) {
                    return new Response(
                            true,
                            "Category removed.",
                            removeCategoryResult,
                            evt.getType()
                    );
                }
                return new Response(
                        false,
                        "Category could not be removed.",
                        "No Body",
                        evt.getType()
                );
        }
        return new Response(
                false,
                "User Type Not Found: " + evt.getType(),
                "No Body",
                evt.getType()
        );
    }
}
