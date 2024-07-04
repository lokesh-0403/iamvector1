package monitoringandnotification;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class MonitoringandNotification {

    private Scheduler scheduler;

    public static void main(String[] args) {
        MonitoringandNotification monitoringAndNotification = new MonitoringandNotification();
        try {
            monitoringAndNotification.setUp();
            monitoringAndNotification.testWebsiteMonitoring();
        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            monitoringAndNotification.tearDown();
        }
    }

    public void setUp() throws SchedulerException {
        // Initialize Quartz Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
        scheduler.start();
    }

    public void testWebsiteMonitoring() throws SchedulerException, InterruptedException {
        // Define the job and tie it to our WebsiteMonitorJob class
        JobDetail job = JobBuilder.newJob(WebsiteMonitorJob.class)
                .withIdentity("websiteMonitorJob", "group1")
                .build();

        // Trigger the job to run now, and then every 5 minutes indefinitely
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("websiteMonitorTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(5)
                        .repeatForever())
                .build();

        // Schedule the job
        scheduler.scheduleJob(job, trigger);

        // Sleep to allow monitoring to run (you can adjust this as needed)
        Thread.sleep(60000); // Wait for 1 minute
    }

    public void tearDown() {
        // Stop Quartz Scheduler
        try {
            if (scheduler != null) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public static class WebsiteMonitorJob implements Job {

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            WebsiteMonitor monitor = new WebsiteMonitor();
            EmailNotifier notifier = new EmailNotifier();

            if (!monitor.isWebsiteUp()) {
                notifier.sendEmailNotification();
            }
        }
    }
}
