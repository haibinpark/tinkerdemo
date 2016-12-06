#!/bin/bash
adb push /Users/Shared/code/LLMWithTinker/app/app-debug.apk /data/local/tmp/com.wkllme.llmwithtinker
adb shell pm install -r "/data/local/tmp/com.wkllme.llmwithtinker"
