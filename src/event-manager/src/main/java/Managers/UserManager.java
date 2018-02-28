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
                            "IEW_User Created",
                            newUserResult,
                            evt.getType()
                    );
                }
                return new Response(
                        false,
                        "IEW_User could not be created.",
                        "No Body",
                        evt.getType()
                );
            case "UPDATE_USER":
                int updateUserResult = Users.updateUser(payload);
                if(updateUserResult == 1) {
                    return new Response(
                            true,
                            "IEW_User Updated",
                            1,
                            evt.getType());

                }
                return new Response(
                        false,
                        "IEW_User could not be updated.",
                        "No Body",
                        evt.getType()
                );
            case"REMOVE_USER":
                String removeUserResult = Users.removeUser(payload);
                return new Response(
                        true,
                        "IEW_User Removed",
                        removeUserResult,
                        evt.getType()
                );
            case"UPDATE_REVIEW":
                Object updateReviewResult = Users.updateReview(payload);
                return new Response(
                        true,
                        "Review Updated",
                        updateReviewResult,
                        evt.getType()
                );
            case"NEW_REVIEW":
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
            case"REMOVE_REVIEW":
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
        }
        return new Response(
                false,
                "IEW_User Type Not Found: " + evt.getType(),
                "No Body",
                evt.getType()
        );
    }
}
