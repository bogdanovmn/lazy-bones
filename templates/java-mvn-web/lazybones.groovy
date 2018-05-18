import static org.apache.commons.io.FileUtils.moveFileToDirectory
import static org.apache.commons.io.FileUtils.moveDirectory

Map<String, String> props = [:]

props.pkgPrefix = ask('Выберите pkgPrefix [com.github.bogdanovmn]: ', 'com.github.bogdanovmn')
props.projectKey = ask('Выберите projectKey [templateproject]: ', 'xxx')
props.pkgBase = props.pkgPrefix + '.' + props.projectKey

//заменяем точки на слэши
//String packageDir = props.pkg.replaceAll(/\./, '/')
//переносим исходник в нужную директорию
//moveFileToDirectory(new File(targetDir, 'App.java'), new File(targetDir, "src/main/java/$packageDir"), true)
//обрабатываем шаблон

Map<String, List<String>> modulesMap = [
    'cli-meta' : ['cli-something'],
    'core' : [],
    'web-meta' : [ 'web-app', 'web-orm' ]
]

static String prefixedModuleName(Map props, String name) {
    return props.projectKey + '-' + name
}

static String stringToDirName(String packageName) {
	return packageName.replaceAll(/[.-]/, '/')
}

static String moduleNameToPackage(String moduleName) {
	return moduleName.replaceAll(/-/, '.')
}

File renameModule(Map props, String moduleName, String parentModuleName) {
    String targetDirName = (parentModuleName ? prefixedModuleName(props, parentModuleName) + '/' : '') + prefixedModuleName(props, moduleName)

    File srcDir = new File(projectDir, moduleName)
    File destDir = new File(projectDir, targetDirName)

    println("rename $srcDir to $destDir")

    println srcDir.renameTo(destDir)

    return destDir
}

def renameDir(File parentDir, String from, String to) {
	File fromDir = new File(parentDir, from)
	File toDir = new File(parentDir, to)
	if (fromDir.exists()) {
		println "rename dir $fromDir to $toDir"
		toDir.mkdirs()
		println fromDir.renameTo(toDir)
	}
}

def copySources(Map<String, String> props, File srcDir, String moduleName) {
	String destDirName = "src/main/java/${stringToDirName(props.pkgBase)}/${stringToDirName(moduleName)}"
    File javaDir = new File(srcDir, "java")
	File destDir = new File(srcDir, destDirName)
	println "rename java dir $javaDir to $destDir"
    if (javaDir.exists()) {
		destDir.mkdirs()
		println javaDir.renameTo(
			destDir
        )
    }
}

def copyResources(Map<String, String> props, File srcDir, String moduleName) {
	File resDir = new File(srcDir, "resources")
	File destDir = new File(srcDir, "src/main/resources")
	if (resDir.exists()) {
		println "rename resource dir $resDir to $destDir"
		destDir.mkdirs()
		println resDir.renameTo(destDir)
	}
}

modulesMap.each {
    parentModuleName, modules ->
        if (!modules.isEmpty()) {
            println("copy meta module $parentModuleName")
            renameModule(props, parentModuleName, null)
            modules.each {
                moduleName ->
                    println("copy module $parentModuleName/$moduleName")
                    File dir = renameModule(props, moduleName, parentModuleName)
					copySources(props, dir, moduleName)
					copyResources(props, dir, moduleName)
            }
        }
        else {
            println("copy module $parentModuleName")
            File dir = renameModule(props, parentModuleName, null)
			copySources(props, dir, parentModuleName)
			copyResources(props, dir, parentModuleName)
		}
}

//processTemplates '**/*.java', props
//processTemplates '**/pom.xml', props