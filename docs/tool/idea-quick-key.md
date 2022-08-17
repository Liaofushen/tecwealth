# File and code templates

## Class

```
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

/**
* ${NAME}
* 
* @project ${PROJECT_NAME}
* @author fushenliao
* @version 1.0.0
* @create ${DATE}
* @modify ${DATE}
  */
  public class ${NAME} {
  }
```

# Live template

## @jb

```
<template name="@jb" value="@lombok.Data&#10;@lombok.Builder(toBuilder = true)&#10;@lombok.NoArgsConstructor&#10;@lombok.AllArgsConstructor" description="" toReformat="false" toShortenFQNames="true">
  <context>
    <option name="JAVA_CODE" value="true" />
  </context>
</template>
@lombok.Data
@lombok.Builder(toBuilder = true)
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
```

## **c

```
<template name="**c" value="**&#10; * $description$&#10; * &#10; * @author fushenliao&#10; * @version 1.0.0&#10; * @create $date$&#10; * @modify $date$&#10; */" shortcut="ENTER" description="类注释" toReformat="true" toShortenFQNames="true">
    <variable name="description" expression="className()" defaultValue="" alwaysStopAt="true" />
    <variable name="date" expression="date()" defaultValue="" alwaysStopAt="false" />
    <context>
    <option name="JAVA_COMMENT" value="true" />
</context>
</template>
**
 * $description$
 * 
 * @author fushenliao
 * @version 1.0.0
 * @create $date$
 * @modify $date$
 */
```

## **m

```
<template name="**m" value="**&#10; * $description$&#10; $params$&#10; $return$" shortcut="ENTER" description="方法注释" toReformat="true" toShortenFQNames="true">
    <variable name="description" expression="methodName()" defaultValue="" alwaysStopAt="true" />
    <variable name="params" expression="groovyScript(&quot;def result='*'; def params=\&quot;${_1}\&quot;.replaceAll('[\\\\[|\\\\]|\\\\s]', '').split(',').toList(); for(i = 0; i &lt; params.size(); i++) if (null != params[i] &amp;&amp; params[i].size() != 0) {result+='\\n * @param ' + params[i] + ' ' + params[i]};return result&quot;, methodParameters()) " defaultValue="" alwaysStopAt="false" />
    <variable name="return" expression="groovyScript(&quot;return _1 != 'void' ? '* @return ' + _1 + '\\n */': '*/'&quot;, methodReturnType())" defaultValue="" alwaysStopAt="false" />
    <context>
    <option name="JAVA_COMMENT" value="true" />
</context>
</template>
**
 * $description$
 $params$
 $return$
```

## //todo

``` 
<template name="//todo" value="//TODO $date$ $time$ by fushenliao: $description$" shortcut="SPACE" description="" toReformat="true" toShortenFQNames="true">
    <variable name="date" expression="date()" defaultValue="" alwaysStopAt="false" />
    <variable name="time" expression="time()" defaultValue="" alwaysStopAt="false" />
    <variable name="description" expression="" defaultValue="to do some" alwaysStopAt="true" />
    <context>
    <option name="JAVA_COMMENT" value="true" />
</context>
</template>
//TODO $date$ $time$ by fushenliao: $description$   
```