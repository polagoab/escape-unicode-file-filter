# Escape Unicode File Filter [![Build Status](https://github.com/polagoab/escape-unicode-file-filter/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/polagoab/escape-unicode-file-filter/actions/workflows/build.yml)

This repository contains the source code for
[escape-unicode-file-filter](http://www.polago.org/escape-unicode-file-filter), 
a Plexus Component that provides an implementation of 
`org.apache.maven.shared.filtering.MavenFileFilter` that translates all non-ASCII
characters to the corresponding java escape sequence properties files.   
It's normally used with maven plugins that uses the Apache Maven Filtering
Shared Component, such as `maven-resources-plugin`. 
