package ${pkgProjectPrefix}.web.infrastructure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.bogdanovmn.common.spring.menu.MenuItem;


@Controller
@RequestMapping("/admin")
public abstract class AbstractVisualAdminController extends AbstractVisualController {
	protected MenuItem currentMenuItem() { return MainMenuItem.NONE; }
}
