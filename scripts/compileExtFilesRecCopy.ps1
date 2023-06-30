# $PSScriptRoot 脚本当前路径变量
$MY_PWD = $PWD
cd $PSScriptRoot

cd ..\src
javac -d ..\classes .\com\example\io\ExtFilesRecCopy.java .\com\example\io\ExtFilter.java .\com\example\io\DirFilter.java

cd ..\classes
$MAINIFEST = "ExtFilesRecCopyManifest.txt"
if(Test-Path $MAINIFEST){
  remove-Item $MAINIFEST
}
New-Item $MAINIFEST
Set-Content $MAINIFEST -NoNewLine  "Main-Class: com.example.io.ExtFilesRecCopy`nClass-Path:"

jar cvfm ExtFilesRecCopy.jar .\ExtFilesRecCopyManifest.txt .\com\example\io\ExtFilesRecCopy.class .\com\example\io\ExtFilter.class .\com\example\io\DirFilter.class

cd $MY_PWD