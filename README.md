Escape Unicode File Filter
==========================

This repository contains the source code for escape-unicode-file-filter, 
a Plexus Component that provides an implementation of 
org.apache.maven.shared.filtering.MavenFileFilter that translates all non-ASCII
characters to the corresponding java escape sequence properties files.   
It's normally used with maven plugins that uses the Apache Maven Filtering
shared component, such as the maven-resources-plugin. 
