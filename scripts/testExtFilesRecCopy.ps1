$MY_PWD = $PWD
cd $PSScriptRoot
cd ..\classes\

# may error if write like this, why?
# $SOURCE_DIR = "D:\MySapce\javaio\filesTest"
# $TARGE_DIR = "D:\MySapce\javaio\filesTest\RecCopy"
# $FILES_EXT = ".txt "
# java -jar .\ExtFilesRecCopy.jar $SOURCE_DIR $TARGE_DIR $FILES_EXT

java -jar .\ExtFilesRecCopy.jar D:\MySapce\javaio\filesTest D:\MySapce\javaio\filesTest\RecCopy .txt

cd $MY_PWD