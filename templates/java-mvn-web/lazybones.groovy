Map<String, String> props = [:]

props.projectKey = ask('Выберите projectKey [templateproject]: ', 'xxx')
props.pkgPrefix = 'com.github.bogdanovmn'
props.pkgProjectPrefix = props.pkgPrefix + '.' + props.projectKey


Map<String, List<String>> modulesMap = [
    'cli-meta' : ['cli-something'],
    'core' : [],
    'web' : [],
    'model' : []
]

static String prefixedModuleName(Map props, String name) {
    return props.projectKey + '-' + name
}

static String stringToDirName(String packageName) {
	return packageName.replaceAll(/[.-]/, '/')
}

File renameModule(Map props, String moduleName, String parentModuleName) {
    String targetDirName = (parentModuleName ? prefixedModuleName(props, parentModuleName) + '/' : '') + prefixedModuleName(props, moduleName)

	return renameDir(
		projectDir as File,
		moduleName,
		targetDirName
	)
}

File renameDir(File parentDir, String from, String to) {
	File fromDir = new File(parentDir, from)
	File toDir = new File(parentDir, to)
	if (fromDir.exists()) {
		println "rename dir $fromDir to $toDir"
		toDir.mkdirs()
		println fromDir.renameTo(toDir)
	}

	return toDir
}

def copySources(Map<String, String> props, File srcDir, String moduleName) {
	renameDir(
		srcDir,
		'java',
		"src/main/java/${stringToDirName(props.pkgProjectPrefix)}/${stringToDirName(moduleName)}"
	)
}

def copyResources(File srcDir) {
	renameDir(
		srcDir,
		'resources',
		"src/main/resources"
	)
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
					copyResources(dir)
			}
        }
        else {
            println("copy module $parentModuleName")
            File dir = renameModule(props, parentModuleName, null)
			copySources(props, dir, parentModuleName)
			copyResources(dir)
		}
}

processTemplates '**/*.java', props
processTemplates '**/pom.xml', props
processTemplates '**/*.properties', props
processTemplates '**/*.yml', props
processTemplates '**/*.html', props