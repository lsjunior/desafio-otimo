package com.github.lsjunior.desafiootimo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Log {

  private static final Logger LOGGER = LoggerFactory.getLogger("com.github.lsjunior.desafiootimo");

  private Log() {
    //
  }

  public static Logger getLogger() {
    return Log.LOGGER;
  }

}
