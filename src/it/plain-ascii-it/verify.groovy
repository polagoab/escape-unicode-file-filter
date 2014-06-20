def line = new File("${basedir}/target/classes/test.properties").text.trim() 
def expected = "test=value"
assert expected.length() == line.length() 
assert expected == line
