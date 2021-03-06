package com.pluralis.plucker.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class ClassFinder {

  static final int SUFFIX_LENGTH = ".class".length();

  
  public Set collectTests() {
    String classPath = System.getProperty("java.class.path");
    Map result = collectFilesInPath(classPath);
    return result.keySet();
  }

  public Map collectFilesInPath(String classPath) {
    Map result = collectFilesInRoots(splitClassPath(classPath));
    return result;
  }

  private Map collectFilesInRoots(List roots) {
    Map result = new HashMap();
    for (Iterator iter = roots.iterator(); iter.hasNext();) {
      gatherFiles(new File((String) iter.next()), "", result);
    }
    return result;
  }

  private void gatherFiles(File classRoot, String classFileName, Map result) {
    File thisRoot = new File(classRoot, classFileName);
    if (thisRoot.isFile()) {
      if (isTestClass(classFileName)) {
        String className = classNameFromFile(classFileName);
        result.put(className, className);
      }
      return;
    }
    String[] contents = thisRoot.list();
    if (contents != null) {
      for (int i = 0; i < contents.length; i++)
        gatherFiles(classRoot,
            classFileName + File.separatorChar + contents[i], result);
    }
  }

  private List splitClassPath(String classPath) {
    List result = new ArrayList();
    String separator = System.getProperty("path.separator");
    StringTokenizer tokenizer = new StringTokenizer(classPath, separator);
    while (tokenizer.hasMoreTokens())
      result.add(tokenizer.nextToken());
    return result;
  }

  protected boolean isTestClass(String classFileName) {
    return classFileName.endsWith(".class") && classFileName.indexOf('$') < 0
        && classFileName.indexOf("Test") > 0;
  }

  protected String classNameFromFile(String classFileName) {
    // convert /a/b.class to a.b
    String s = classFileName.substring(0, classFileName.length()
        - SUFFIX_LENGTH);
    String s2 = s.replace(File.separatorChar, '.');
    if (s2.startsWith("."))
      return s2.substring(1);
    return s2;
  }
}
