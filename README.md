# Escape Unicode File Filter [![Build Status](https://travis-ci.org/polagoab/escape-unicode-file-filter.svg?branch=master)](https://travis-ci.org/polagoab/escape-unicode-file-filter)

This repository contains the source code for
[escape-unicode-file-filter](http://www.polago.org/escape-unicode-file-filter), 
a Plexus Component that provides an implementation of 
`org.apache.maven.shared.filtering.MavenFileFilter` that translates all non-ASCII
characters to the corresponding java escape sequence properties files.   
It's normally used with maven plugins that uses the Apache Maven Filtering
Shared Component, such as `maven-resources-plugin`. 
