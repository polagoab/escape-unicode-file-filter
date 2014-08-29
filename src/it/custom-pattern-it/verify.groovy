def line = new File("${basedir}/target/classes/test.data").getText('UTF-8').trim()
def expected = "test=value" + "\u00e4"
assert expected.length() == line.length() 
assert expected == line

line = new File("${basedir}/target/classes/test.txt").getText('UTF-8').trim()
expected = "test=value" + "\\" + "u00e4"
assert expected.length() == line.length() 
assert expected == line
