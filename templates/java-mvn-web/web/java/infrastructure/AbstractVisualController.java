package ${pkgProjectPrefix}.web.infrastructure;

import ${pkgProjectPrefix}.web.infrastructure.config.mustache.Layout;
import com.samskivert.mustache.Mustache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Value;
import com.github.bogdanovmn.common.spring.menu.MenuBuilder;
import com.github.bogdanovmn.common.spring.menu.MenuItem;


import java.util.Map;

public abstract class AbstractVisualController extends AbstractController {
	@Autowired
	private Mustache.Compiler compiler;
	@Autowired
	private MenuBuilder menuBuilder;

	@Value("\${server.servlet.context-path:}")
	private String contextPath;

	@ModelAttribute("layout")
	public Mustache.Lambda layout(Map<String, Object> model) {
		return new Layout(compiler, "main", contextPath);
	}

	@ModelAttribute
	public void addCommonAttributes(Model model) {
		model.addAttribute("menu",
			menuBuilder
				.setSelectedItem(currentMenuItem())
				.setUser(getUser())
				.build()
		);
		model.addAttribute("userName", getUser().getName());
	}

	protected abstract MenuItem currentMenuItem();
	protected MenuItem currentAdminMenuItem() { return MainMenuItem.NONE; }
}
