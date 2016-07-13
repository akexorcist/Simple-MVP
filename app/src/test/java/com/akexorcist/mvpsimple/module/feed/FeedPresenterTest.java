package com.akexorcist.mvpsimple.module.feed;

import com.akexorcist.mvpsimple.network.model.PostList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by Akexorcist on 7/14/16 AD.
 */
public class FeedPresenterTest {
    @Mock
    private FeedContractor.View feedViewContractor;
    @Captor
    private ArgumentCaptor<PostList> postListArgumentCaptor;
    private FeedPresenter feedPresenter;

//    @Captor
//    private ArgumentCaptor<LoadTasksCallback> mLoadTasksCallbackCaptor;


    @Before
    public void setupTasksPresenter() {
        MockitoAnnotations.initMocks(this);
        feedPresenter = FeedPresenter.createPresenter(feedViewContractor);
    }

    @Test
    public void loadAllTasksFromRepositoryAndLoadIntoView() {
        // Given an initialized TasksPresenter with initialized tasks
        // When loading of Tasks is requested
//        feedPresenter.loadPostList();
//
//        // Callback is captured and invoked with stubbed tasks
//        verify(mTasksRepository).getTasks(postListArgumentCaptor.capture());
//        mLoadTasksCallbackCaptor.getValue().onTasksLoaded(TASKS);
//
//        // Then progress indicator is shown
//        verify(mTasksView).setLoadingIndicator(true);
//        // Then progress indicator is hidden and all tasks are shown in UI
//        verify(mTasksView).setLoadingIndicator(false);
//        ArgumentCaptor<List> showTasksArgumentCaptor = ArgumentCaptor.forClass(List.class);
//        verify(mTasksView).showTasks(showTasksArgumentCaptor.capture());
//        assertTrue(showTasksArgumentCaptor.getValue().size() == 3);
    }
}
