package com.model;

import java.io.*;

public class WindowsUtils{

  public WindowsUtils() {  }

  public static boolean isAdmin(String admin) {

    String groups[] =
      (new com.sun.security.auth.module.NTSystem()).getGroupIDs();
    for (String group : groups) {
          if (group.equals("S-1-5-32-544")){
            String admin1=(new com.sun.security.auth.module.NTSystem()).getName();
            if(admin.equals(admin1)){

              return true;
            }
            }
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
    //System.out.println("Current user is admin ? " + WindowsUtils.isAdmin());
  }
}

