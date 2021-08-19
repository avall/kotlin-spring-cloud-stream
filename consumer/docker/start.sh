#java -jar -Xss1024k  -Xms128m  -Xmx128m  -XX:+UnlockExperimentalVMOptions  -XX:+UseCGroupMemoryLimitForHeap -XX:CICompilerCount=2 -XX:+UseG1GC   -XX:+UseStringDeduplication   -XX:MaxGCPauseMillis=1000  -XX:+ParallelRefProcEnabled   -XX:+PrintReferenceGC  -XX:G1HeapRegionSize=4m application.jar
#gradle clean assemble
#gradle bootRun
#java -jar -Xms256m -Xmx512m application/build/libs/application-1.0.0.jar