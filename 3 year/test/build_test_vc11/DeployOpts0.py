# coding=utf-8

###### Options ######

options={}

options["<config>"]=ur'Default'
options["<os>"]=ur'android'
options["appdata"]=ur'C:/Users/maks/AppData/Roaming/MarmaladeTools'
options["build"]=ur'ARM GCC Debug'
options["cmdline"]=['D:/Marmalade/6.3/s3e/makefile_builder/mkb.py', 'c:/Users/maks/Documents/test/test.mkb', '--default-buildenv=vc11', '--msvc-project', '--deploy-only']
options["configlist"]=ur'Default ,'
options["device"]=ur'"{"localdir}/Deploy.py" device "{"tempdir}/DeployOpts.py" {hasmkb}'
options["dir"]=ur'c:/Users/maks/Documents/test/build_test_vc11'
options["excluded_deploy_platforms"]=ur'NONE'
options["expires"]=ur'Tue Jul 16 15:21:12 2013'
options["folder"]=ur'"{"localdir}/Deploy.py" folder "{"tempdir}/DeployOpts.py" {hasmkb}'
options["generated"]=ur'Sat Jul  6 15:21:12 2013'
options["hasmkb"]=ur'nomkb'
options["helpfile"]=ur'../docs/Deployment.chm'
options["helppage"]=ur'0'
options["lastdir"]=ur'C:/Users/maks/Documents/test/build_test_vc11'
options["license_type"]=ur'Evaluation'
options["licensed_platforms"]=ur'NONE'
options["mac_address"]=ur'20cf30c694e3'
options["mkb"]=ur'c:/Users/maks/Documents/test/test.mkb'
options["mkbdir"]=ur'c:/Users/maks/Documents/test'
options["mkbfile"]=ur'test.mkb'
options["outdir"]=ur'c:/Users/maks/Documents/test/build_test_vc11'
options["renew"]=ur'Sun Jul  7 15:21:12 2013'
options["required_splashscreen"]=ur'FALSE'
options["resdir"]=ur'd:/marmalade/6.3/tools/DeployTool/'
options["s3edir"]=ur'D:/Marmalade/6.3/s3e/'
options["sdk_version"]=ur'6.3.1 [345893] (Win32)'
options["stage"]=ur'build'
options["target"]=ur'test'
options["task"]=ur'Default'
options["tasktype"]=ur'Package'
options["username"]=ur'maks9416@gmail.com'

###### Tasks ######

tasks=[]

### Task Default:android ###

t={}
tasks.append(t)
t["tasktype"]="Package, Install and Run"
t["<config>"]="Default"
t["os"]="android"
t["endDir"]="c:/Users/maks/Documents/test/build_test_vc11/deployments/default/android/debug/arm"
