package ${pkgProjectPrefix}.web.app.infrastructure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public abstract class AbstractAdminController extends AbstractController {
}
