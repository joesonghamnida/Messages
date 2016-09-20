import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by joe on 20/09/2016.
 */
public class Main {

    static User user;
    static ArrayList<Message> messageList = new ArrayList<>();

    public static void main(String[] args) {

        Spark.init();

        //three parameters: path, route, response transformer
        get("/",
                ((request, response) -> {
                    HashMap h = new HashMap();
                    if (user == null) {
                        return new ModelAndView(h, "index.html");
                    } else {
                        h.put("name", user.name);
                        h.put("messages",messageList);
                        return new ModelAndView(h, "messages.html");
                    }
                }),
                new MustacheTemplateEngine()
        );

        post("/create-user",
                ((request, response) -> {
                    String name = request.queryParams("name");
                    user = new User(name);
                    response.redirect("/");

                    return "";
                }));

        post("/create-message",
                (request, response) -> {
                    String message = request.queryParams("message");
                    Message m = new Message(message);
                    messageList.add(m);
                    response.redirect("/");
                    return "";
                });
    }
}
