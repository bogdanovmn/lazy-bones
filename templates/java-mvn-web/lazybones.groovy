import static org.apache.commons.io.FileUtils.moveFileToDirectory

Map<String, String> props = [:]

props.groupId = ask('Выберите projectKey [myproject]: ', 'templateproject')

//метод processTemplates обрабатывает шаблоны, заменяя меаркеры значениями из мапы.
processTemplates 'pom.xml', props

//заменяем точки на слэши
String packageDir = props.pkg.replaceAll(/\./, '/')
//переносим исходник в нужную директорию
moveFileToDirectory(new File(targetDir, 'App.java'), new File(targetDir, "src/main/java/$packageDir"), true)
//обрабатываем шаблон
processTemplates 'src/main/java/**/App.java', props
