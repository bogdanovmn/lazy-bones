package ${pkgProjectPrefix}.web.app.admin;

import ${pkgProjectPrefix}.web.app.AbstractVisualController;
import ${pkgProjectPrefix}.web.app.HeadMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public abstract class AbstractVisualAdminController extends AbstractVisualController {
	protected HeadMenu.ITEM currentMenuItem() { return HeadMenu.ITEM.ADMIN; }
}
