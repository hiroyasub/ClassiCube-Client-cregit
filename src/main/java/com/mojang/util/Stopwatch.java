begin_unit|revision:1.0.0;language:Java;cregit-version:0.0.1
begin_package
package|package
name|com
operator|.
name|mojang
operator|.
name|util
package|;
end_package

begin_import
import|import
name|java
operator|.
name|text
operator|.
name|NumberFormat
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|LinkedList
import|;
end_import

begin_import
import|import
name|java
operator|.
name|util
operator|.
name|List
import|;
end_import

begin_class
specifier|public
class|class
name|Stopwatch
block|{
comment|/** 	 * Inner class to hold data about one task executed within the stop watch. 	 */
specifier|public
specifier|static
specifier|final
class|class
name|TaskInfo
block|{
specifier|private
specifier|final
name|String
name|taskName
decl_stmt|;
specifier|private
specifier|final
name|long
name|timeMillis
decl_stmt|;
name|TaskInfo
parameter_list|(
name|String
name|taskName
parameter_list|,
name|long
name|timeMillis
parameter_list|)
block|{
name|this
operator|.
name|taskName
operator|=
name|taskName
expr_stmt|;
name|this
operator|.
name|timeMillis
operator|=
name|timeMillis
expr_stmt|;
block|}
comment|/** 		 * Return the name of this task. 		 */
specifier|public
name|String
name|getTaskName
parameter_list|()
block|{
return|return
name|taskName
return|;
block|}
comment|/** 		 * Return the time in milliseconds this task took. 		 */
specifier|public
name|long
name|getTimeMillis
parameter_list|()
block|{
return|return
name|timeMillis
return|;
block|}
comment|/** 		 * Return the time in seconds this task took. 		 */
specifier|public
name|double
name|getTimeSeconds
parameter_list|()
block|{
return|return
name|timeMillis
operator|/
literal|1000.0
return|;
block|}
block|}
comment|/** 	 * Identifier of this stop watch. Handy when we have output from multiple 	 * stop watches and need to distinguish between them in log or console 	 * output. 	 */
specifier|private
specifier|final
name|String
name|id
decl_stmt|;
specifier|private
name|boolean
name|keepTaskList
init|=
literal|true
decl_stmt|;
specifier|private
specifier|final
name|List
argument_list|<
name|TaskInfo
argument_list|>
name|taskList
init|=
operator|new
name|LinkedList
argument_list|<
name|TaskInfo
argument_list|>
argument_list|()
decl_stmt|;
comment|/** Start time of the current task */
specifier|private
name|long
name|startTimeMillis
decl_stmt|;
comment|/** Is the stop watch currently running? */
specifier|private
name|boolean
name|running
decl_stmt|;
comment|/** Name of the current task */
specifier|private
name|String
name|currentTaskName
decl_stmt|;
specifier|private
name|TaskInfo
name|lastTaskInfo
decl_stmt|;
specifier|private
name|int
name|taskCount
decl_stmt|;
comment|/** Total running time */
specifier|private
name|long
name|totalTimeMillis
decl_stmt|;
comment|/** 	 * Construct a new stop watch. Does not start any task. 	 */
specifier|public
name|Stopwatch
parameter_list|()
block|{
name|id
operator|=
literal|""
expr_stmt|;
block|}
comment|/** 	 * Construct a new stop watch with the given id. Does not start any task. 	 *  	 * @param id 	 *			identifier for this stop watch. Handy when we have output from 	 *			multiple stop watches and need to distinguish between them. 	 */
specifier|public
name|Stopwatch
parameter_list|(
name|String
name|id
parameter_list|)
block|{
name|this
operator|.
name|id
operator|=
name|id
expr_stmt|;
block|}
comment|/** 	 * Return the last task as a TaskInfo object. 	 */
specifier|public
name|TaskInfo
name|getLastTaskInfo
parameter_list|()
throws|throws
name|IllegalStateException
block|{
if|if
condition|(
name|lastTaskInfo
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|IllegalStateException
argument_list|(
literal|"No tasks run: can't get last task info"
argument_list|)
throw|;
block|}
return|return
name|lastTaskInfo
return|;
block|}
comment|/** 	 * Return the name of the last task. 	 */
specifier|public
name|String
name|getLastTaskName
parameter_list|()
throws|throws
name|IllegalStateException
block|{
if|if
condition|(
name|lastTaskInfo
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|IllegalStateException
argument_list|(
literal|"No tasks run: can't get last task name"
argument_list|)
throw|;
block|}
return|return
name|lastTaskInfo
operator|.
name|getTaskName
argument_list|()
return|;
block|}
comment|/** 	 * Return the time taken by the last task. 	 */
specifier|public
name|long
name|getLastTaskTimeMillis
parameter_list|()
throws|throws
name|IllegalStateException
block|{
if|if
condition|(
name|lastTaskInfo
operator|==
literal|null
condition|)
block|{
throw|throw
operator|new
name|IllegalStateException
argument_list|(
literal|"No tasks run: can't get last task interval"
argument_list|)
throw|;
block|}
return|return
name|lastTaskInfo
operator|.
name|getTimeMillis
argument_list|()
return|;
block|}
comment|/** 	 * Return the number of tasks timed. 	 */
specifier|public
name|int
name|getTaskCount
parameter_list|()
block|{
return|return
name|taskCount
return|;
block|}
comment|/** 	 * Return an array of the data for tasks performed. 	 */
specifier|public
name|TaskInfo
index|[]
name|getTaskInfo
parameter_list|()
block|{
if|if
condition|(
operator|!
name|keepTaskList
condition|)
block|{
throw|throw
operator|new
name|UnsupportedOperationException
argument_list|(
literal|"Task info is not being kept!"
argument_list|)
throw|;
block|}
return|return
name|taskList
operator|.
name|toArray
argument_list|(
operator|new
name|TaskInfo
index|[
name|taskList
operator|.
name|size
argument_list|()
index|]
argument_list|)
return|;
block|}
comment|/** 	 * Return the total time in milliseconds for all tasks. 	 */
specifier|public
name|long
name|getTotalTimeMillis
parameter_list|()
block|{
return|return
name|totalTimeMillis
return|;
block|}
comment|/** 	 * Return the total time in seconds for all tasks. 	 */
specifier|public
name|double
name|getTotalTimeSeconds
parameter_list|()
block|{
return|return
name|totalTimeMillis
operator|/
literal|1000.0
return|;
block|}
comment|/** 	 * Return whether the stop watch is currently running. 	 */
specifier|public
name|boolean
name|isRunning
parameter_list|()
block|{
return|return
name|running
return|;
block|}
comment|/** 	 * Return a string with a table describing all tasks performed. For custom 	 * reporting, call getTaskInfo() and use the task info directly. 	 */
specifier|public
name|String
name|prettyPrint
parameter_list|()
block|{
name|StringBuilder
name|sb
init|=
operator|new
name|StringBuilder
argument_list|(
name|shortSummary
argument_list|()
argument_list|)
decl_stmt|;
name|sb
operator|.
name|append
argument_list|(
literal|'\n'
argument_list|)
expr_stmt|;
if|if
condition|(
operator|!
name|keepTaskList
condition|)
block|{
name|sb
operator|.
name|append
argument_list|(
literal|"No task info kept"
argument_list|)
expr_stmt|;
block|}
else|else
block|{
name|sb
operator|.
name|append
argument_list|(
literal|"-----------------------------------------\n"
argument_list|)
expr_stmt|;
name|sb
operator|.
name|append
argument_list|(
literal|"ms	 %	 Task name\n"
argument_list|)
expr_stmt|;
name|sb
operator|.
name|append
argument_list|(
literal|"-----------------------------------------\n"
argument_list|)
expr_stmt|;
name|NumberFormat
name|nf
init|=
name|NumberFormat
operator|.
name|getNumberInstance
argument_list|()
decl_stmt|;
name|nf
operator|.
name|setMinimumIntegerDigits
argument_list|(
literal|5
argument_list|)
expr_stmt|;
name|nf
operator|.
name|setGroupingUsed
argument_list|(
literal|false
argument_list|)
expr_stmt|;
name|NumberFormat
name|pf
init|=
name|NumberFormat
operator|.
name|getPercentInstance
argument_list|()
decl_stmt|;
name|pf
operator|.
name|setMinimumIntegerDigits
argument_list|(
literal|3
argument_list|)
expr_stmt|;
name|pf
operator|.
name|setGroupingUsed
argument_list|(
literal|false
argument_list|)
expr_stmt|;
for|for
control|(
name|TaskInfo
name|task
range|:
name|getTaskInfo
argument_list|()
control|)
block|{
name|sb
operator|.
name|append
argument_list|(
name|nf
operator|.
name|format
argument_list|(
name|task
operator|.
name|getTimeMillis
argument_list|()
argument_list|)
argument_list|)
operator|.
name|append
argument_list|(
literal|"  "
argument_list|)
expr_stmt|;
name|sb
operator|.
name|append
argument_list|(
name|pf
operator|.
name|format
argument_list|(
name|task
operator|.
name|getTimeSeconds
argument_list|()
operator|/
name|getTotalTimeSeconds
argument_list|()
argument_list|)
argument_list|)
operator|.
name|append
argument_list|(
literal|"  "
argument_list|)
expr_stmt|;
name|sb
operator|.
name|append
argument_list|(
name|task
operator|.
name|getTaskName
argument_list|()
argument_list|)
operator|.
name|append
argument_list|(
literal|"\n"
argument_list|)
expr_stmt|;
block|}
block|}
return|return
name|sb
operator|.
name|toString
argument_list|()
return|;
block|}
comment|/** 	 * Determine whether the TaskInfo array is built over time. Set this to 	 * "false" when using a StopWatch for millions of intervals, or the task 	 * info structure will consume excessive memory. Default is "true". 	 */
specifier|public
name|void
name|setKeepTaskList
parameter_list|(
name|boolean
name|keepTaskList
parameter_list|)
block|{
name|this
operator|.
name|keepTaskList
operator|=
name|keepTaskList
expr_stmt|;
block|}
comment|/** 	 * Return a short description of the total running time. 	 */
specifier|public
name|String
name|shortSummary
parameter_list|()
block|{
return|return
literal|"StopWatch '"
operator|+
name|id
operator|+
literal|"': running time (millis) = "
operator|+
name|getTotalTimeMillis
argument_list|()
return|;
block|}
comment|/** 	 * Start an unnamed task. The results are undefined if {@link #stop()} or 	 * timing methods are called without invoking this method. 	 *  	 * @see #stop() 	 */
specifier|public
name|void
name|start
parameter_list|()
throws|throws
name|IllegalStateException
block|{
name|start
argument_list|(
literal|""
argument_list|)
expr_stmt|;
block|}
comment|/** 	 * Start a named task. The results are undefined if {@link #stop()} or 	 * timing methods are called without invoking this method. 	 *  	 * @param taskName 	 *			the name of the task to start 	 * @see #stop() 	 */
specifier|public
name|void
name|start
parameter_list|(
name|String
name|taskName
parameter_list|)
throws|throws
name|IllegalStateException
block|{
if|if
condition|(
name|running
condition|)
block|{
throw|throw
operator|new
name|IllegalStateException
argument_list|(
literal|"Can't start StopWatch: it's already running"
argument_list|)
throw|;
block|}
name|startTimeMillis
operator|=
name|System
operator|.
name|currentTimeMillis
argument_list|()
expr_stmt|;
name|running
operator|=
literal|true
expr_stmt|;
name|currentTaskName
operator|=
name|taskName
expr_stmt|;
block|}
comment|/** 	 * Stop the current task. The results are undefined if timing methods are 	 * called without invoking at least one pair {@link #start()} / 	 * {@link #stop()} methods. 	 *  	 * @see #start() 	 */
specifier|public
name|void
name|stop
parameter_list|()
throws|throws
name|IllegalStateException
block|{
if|if
condition|(
operator|!
name|running
condition|)
block|{
throw|throw
operator|new
name|IllegalStateException
argument_list|(
literal|"Can't stop StopWatch: it's not running"
argument_list|)
throw|;
block|}
name|long
name|lastTime
init|=
name|System
operator|.
name|currentTimeMillis
argument_list|()
operator|-
name|startTimeMillis
decl_stmt|;
name|totalTimeMillis
operator|+=
name|lastTime
expr_stmt|;
name|lastTaskInfo
operator|=
operator|new
name|TaskInfo
argument_list|(
name|currentTaskName
argument_list|,
name|lastTime
argument_list|)
expr_stmt|;
if|if
condition|(
name|keepTaskList
condition|)
block|{
name|taskList
operator|.
name|add
argument_list|(
name|lastTaskInfo
argument_list|)
expr_stmt|;
block|}
operator|++
name|taskCount
expr_stmt|;
name|running
operator|=
literal|false
expr_stmt|;
name|currentTaskName
operator|=
literal|null
expr_stmt|;
block|}
comment|/** 	 * Return an informative string describing all tasks performed For custom 	 * reporting, call {@code getTaskInfo()} and use the task info directly. 	 */
annotation|@
name|Override
specifier|public
name|String
name|toString
parameter_list|()
block|{
name|StringBuilder
name|sb
init|=
operator|new
name|StringBuilder
argument_list|(
name|shortSummary
argument_list|()
argument_list|)
decl_stmt|;
if|if
condition|(
name|keepTaskList
condition|)
block|{
for|for
control|(
name|TaskInfo
name|task
range|:
name|getTaskInfo
argument_list|()
control|)
block|{
name|sb
operator|.
name|append
argument_list|(
literal|"; ["
argument_list|)
operator|.
name|append
argument_list|(
name|task
operator|.
name|getTaskName
argument_list|()
argument_list|)
operator|.
name|append
argument_list|(
literal|"] took "
argument_list|)
operator|.
name|append
argument_list|(
name|task
operator|.
name|getTimeMillis
argument_list|()
argument_list|)
expr_stmt|;
name|long
name|percent
init|=
name|Math
operator|.
name|round
argument_list|(
literal|100.0
operator|*
name|task
operator|.
name|getTimeSeconds
argument_list|()
operator|/
name|getTotalTimeSeconds
argument_list|()
argument_list|)
decl_stmt|;
name|sb
operator|.
name|append
argument_list|(
literal|" = "
argument_list|)
operator|.
name|append
argument_list|(
name|percent
argument_list|)
operator|.
name|append
argument_list|(
literal|"%"
argument_list|)
expr_stmt|;
block|}
block|}
else|else
block|{
name|sb
operator|.
name|append
argument_list|(
literal|"; no task info kept"
argument_list|)
expr_stmt|;
block|}
return|return
name|sb
operator|.
name|toString
argument_list|()
return|;
block|}
block|}
end_class

end_unit

