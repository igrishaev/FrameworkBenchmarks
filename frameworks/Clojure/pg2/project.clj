(defproject hello "0.1.0-SNAPSHOT"

  :description
  "FIXME: write description"

  :url
  "http://example.com/FIXME"

  :license
  {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
   :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies
  [[org.clojure/clojure "1.11.1"]
   [http-kit "2.8.0"]
   [com.github.igrishaev/pg2-core

    #_"0.1.13-20240604.143211-4"
    "0.1.13-20240604.153136-5"]]

  :main
  ^:skip-aot hello.core

  :target-path
  "target/%s"

  :uberjar-name
  "server.jar"

  :profiles
  {:uberjar
   {:aot :all
    :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
