package ${pkgProjectPrefix}.web.app.admin;

import ${pkgProjectPrefix}.web.app.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public abstract class AbstractAdminController extends AbstractController {
}
