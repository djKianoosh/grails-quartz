/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.groovy.grails.plugins.quartz

import org.quartz.JobDetail
import org.quartz.Scheduler
import org.quartz.Trigger

/**
 * JobDescriptor that stores information about the Quartz job to show on webapp.
 *
 * @author Marco Mornati (mmornati@byte-code.com)
 * @author Sergey Nebolsin (nebolsin@gmail.com)
 *
 * @since 0.4
 */
class JobDescriptor {
    JobDetail jobDetail

    List<TriggerDescriptor> triggerDescriptors

    static build(JobDetail jobDetail, Scheduler scheduler) {
        def job = new JobDescriptor(jobDetail: jobDetail)
        job.triggerDescriptors = scheduler.getTriggersOfJob(job.name, job.group).collect { Trigger trigger ->
            TriggerDescriptor.build(job, trigger, scheduler)
        }
        job
    }

    String getName() {
        jobDetail.name
    }

    String getGroup() {
        jobDetail.group
    }
}