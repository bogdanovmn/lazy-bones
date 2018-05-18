package ${pkgPrefix}.${projectKey}.web.app.admin;

import ${pkgPrefix}.${projectKey}.web.app.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public abstract class AbstractAdminController extends AbstractController {
}
