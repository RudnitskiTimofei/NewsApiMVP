package by.it.trudnitski.newsapimvp.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NewsPresenterTest {

    @Mock
    NewsPresenter iNewsPresenter;

    @Mock
    NewsDetailPresenter newsDetailPresenter;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUpScheduler();
    }

    @Test
    public void loadNewsTest() {
      iNewsPresenter.loadNews();
      verify(iNewsPresenter, atLeastOnce()).loadNews();
    }


    @Test
    public void getNewsResponseTest() {
        iNewsPresenter.getNewsResponse();
        verify(iNewsPresenter, atLeastOnce()).getNewsResponse();
    }

    @Test
    public void getMessageTest() {
       iNewsPresenter.getMessage(anyString());
       verify(iNewsPresenter, atLeastOnce()).getMessage(anyString());
    }

    @Test
    public void openNewActivityTest() {
       iNewsPresenter.openNewActivity(anyInt());
       verify(iNewsPresenter, atLeastOnce()).openNewActivity(anyInt());
    }

    @Test
    public void detailPresenterTest(){
        newsDetailPresenter.sendMessage(anyString(), anyString(), anyString(), anyString() );
        verify(newsDetailPresenter, atLeastOnce())
                .sendMessage(anyString(), anyString(), anyString(), anyString());
    }


    public void setUpScheduler() {

        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };
        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }
}
