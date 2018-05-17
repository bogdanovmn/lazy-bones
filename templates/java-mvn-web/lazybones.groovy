import static org.apache.commons.io.FileUtils.moveFileToDirectory

Map<String, String> props = [:]

props.pkgPrefix = ask('Выберите pkgPrefix [com.github.bogdanovmn]: ', 'com.github.bogdanovmn')
props.projectKey = ask('Выберите projectKey [templateproject]: ', 'templateproject')


//заменяем точки на слэши
//String packageDir = props.pkg.replaceAll(/\./, '/')
//переносим исходник в нужную директорию
//moveFileToDirectory(new File(targetDir, 'App.java'), new File(targetDir, "src/main/java/$packageDir"), true)
//обрабатываем шаблон
processTemplates 'src/main/java/**/*.java', props
processTemplates '**/pom.xml', props

Map<String, List<String>> modulesMap = [
        'cli-meta' : ['cli-something'],
        'core' : [],
        'web-meta' : [ 'web-app', 'web-orm' ]
]

modulesMap.each {
    baseModuleName, modules ->
        File baseDir = new File(projectDir as File, baseModuleName)
        baseDir.renameTo(props.projectKey + '-' + baseModuleName)

        modules.each {
            moduleName ->
                File moduleDir = new File(baseDir, moduleName)
                moduleDir.renameTo(props.projectKey + '-' + moduleName)

        }
}