package com.amjadalwareh.androidutils;

import com.amjadalwareh.AndroidLogDetector;
import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static com.android.tools.lint.detector.api.ApiKt.CURRENT_API;

public class AnIssueRegistry extends IssueRegistry {

    @Override
    public int getApi() {
        return CURRENT_API;
    }

    @NotNull
    @Override
    public List<Issue> getIssues() {
        return Collections.singletonList(AndroidLogDetector.issue);
    }
}