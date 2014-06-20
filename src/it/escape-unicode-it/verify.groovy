def line = new File("${basedir}/target/classes/test.properties").text.trim() 
def expected = "test=value" + "\\" + "u00e4"
assert expected.length() == line.length() 
assert expected == line
