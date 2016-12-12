#!/bin/bash
adb -s LJJVBQ5HUG59T89D push /Users/Shared/code/LLMWithTinker/app/build/bakApk/app-debug-1206-15-03-20.apk /data/local/tmp/com.wkllme.llmwithtinker
adb -s LJJVBQ5HUG59T89D shell pm install -r "/data/local/tmp/com.wkllme.llmwithtinker"
