package ${pkgPrefix}.${projectKey}.web.app.admin;

import ${pkgPrefix}.${projectKey}.web.app.AbstractVisualController;
import ${pkgPrefix}.${projectKey}.web.app.HeadMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public abstract class AbstractVisualAdminController extends AbstractVisualController {
	protected HeadMenu.ITEM currentMenuItem() { return HeadMenu.ITEM.ADMIN; }
}
