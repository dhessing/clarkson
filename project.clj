(defproject
  clarkson
  "0.1.0-SNAPSHOT"
  :description
  "FIXME: write description"
  :url
  "http://example.com/FIXME"
  :dependencies
  [[prone "0.8.0"]
   [selmer "0.7.7"]
   [com.taoensso/tower "3.0.2"]
   [markdown-clj "0.9.58" :exclusions [com.keminglabs/cljx]]
   [org.clojure/clojurescript "0.0-2511"]
   [im.chit/cronj "1.4.3"]
   [com.taoensso/timbre "3.3.1"]
   [cljs-ajax "0.3.3"]
   [noir-exception "0.2.3"]
   [lib-noir "0.9.5"]
   [org.clojure/clojure "1.6.0"]
   [environ "1.0.0"]
   [ring-server "0.3.1"]
   [reagent-forms "0.2.9"]
   [secretary "1.2.1"]]
  :repl-options
  {:init-ns clarkson.repl}
  :jvm-opts
  ["-server"]
  :plugins
  [[lein-ring "0.8.13"]
   [lein-environ "1.0.0"]
   [lein-ancient "0.5.5"]
   [lein-cljsbuild "1.0.4"]]
  :ring
  {:handler clarkson.handler/app,
   :init clarkson.handler/init,
   :destroy clarkson.handler/destroy}
  :profiles
  {:uberjar
   {:cljsbuild
    {:jar true,
     :builds
     {:app
      {:source-paths ["env/prod/cljs"],
       :compiler {:optimizations :advanced, :pretty-print false}}}},
    :hooks [leiningen.cljsbuild],
    :omit-source true,
    :env {:production true},
    :aot :all},
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}},
   :dev
   {:cljsbuild {:builds {:app {:source-paths ["env/dev/cljs"]}}},
    :dependencies
    [[ring-mock "0.1.5"]
     [ring/ring-devel "1.3.2"]
     [pjstadig/humane-test-output "0.6.0"]],
    :injections
    [(require 'pjstadig.humane-test-output)
     (pjstadig.humane-test-output/activate!)],
    :env {:dev true}}}
  :cljsbuild
  {:builds
   {:app
    {:source-paths ["src-cljs"],
     :compiler
     {:output-dir "resources/public/js/out",
      :externs ["react/externs/react.js"],
      :optimizations :none,
      :output-to "resources/public/js/app.js",
      :source-map "resources/public/js/out.js.map",
      :pretty-print true}}}}
  :uberjar-name
  "clarkson.jar"
  :min-lein-version "2.0.0")