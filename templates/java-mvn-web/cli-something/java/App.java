package ${pkgProjectPrefix}.cli.something;


import ${pkgPrefix}.cmdline.CmdLineAppBuilder;

public class App {
	public static void main(String[] args) throws Exception {
		new CmdLineAppBuilder(args)
			.withJarName("something")
			.withDescription("")
//			.withArg("option-name", "option descr")
			.withEntryPoint(
				cmdLine -> {

				}
			).build().run();
	}
}
