/*
 * Copyright 2021 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.gradle;

import org.junit.jupiter.api.Test;
import org.openrewrite.DocumentExample;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.gradle.Assertions.buildGradle;
import static org.openrewrite.gradle.Assertions.buildGradleKts;

class RemoveRepositoryTest implements RewriteTest {

    @DocumentExample
    @Test
    void removeJcenter() {
        rewriteRun(
          spec -> spec.recipe(new RemoveRepository("jcenter")),
          buildGradle(
            """
              repositories {
                  jcenter()
              }
              """,
            """
              repositories {
                 \s
              }
              """
          )
        );
    }

    @Test
    void removeJcenterKotlin() {
        rewriteRun(
          spec -> spec.recipe(new RemoveRepository("jcenter")),
          buildGradleKts(
            """
              repositories {
                  jcenter()
              }
              """,
            """
              repositories {
              }
              """
          )
        );
    }
}
