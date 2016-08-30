//
// Created by ZKC on 2016-08-28.
//
#include "func_ndk__NdkUtil.h"
#include "string.h"

JNIEXPORT jstring JNICALL Java_func_ndk_1_NdkUtil_getStringFromNative
  (JNIEnv *env, jobject obj){
  return (*env)->NewStringUTF(env,"string from c");
}

