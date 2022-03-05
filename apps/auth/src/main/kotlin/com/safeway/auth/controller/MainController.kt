@Controller
public class WebController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "That's pretty basic!";
    }

}
