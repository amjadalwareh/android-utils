package com.amjadalwareh;

import com.android.tools.lint.checks.AccessibilityDetector;
import com.android.tools.lint.client.api.JavaEvaluator;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.SourceCodeScanner;
import com.intellij.psi.PsiMethod;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;

import java.util.ArrayList;
import java.util.List;

public class AndroidLogDetector extends Detector implements SourceCodeScanner {

    private static Implementation IMPLEMENTATION = new Implementation(AndroidLogDetector.class, Scope.JAVA_FILE_SCOPE);

    public static Issue issue = Issue.create("AndroidLogDetector", "You can not use Android Log",
            "No need", Category.CORRECTNESS, 9, Severity.ERROR, IMPLEMENTATION);

    @Nullable
    @Override
    public List<String> getApplicableMethodNames() {
        List<String> names = new ArrayList<>();
        names.add("tag");
        names.add("format");
        names.add("v");
        names.add("d");
        names.add("i");
        names.add("w");
        names.add("e");
        return names;
    }

    @Override
    public void visitMethodCall(@NotNull JavaContext context, @NotNull UCallExpression node, @NotNull PsiMethod method) {
        super.visitMethodCall(context, node, method);
        JavaEvaluator evaluator = context.getEvaluator();
//        if (evaluator.isMemberInClass(method, "android.util.LOG"))
//            context.report(AccessibilityDetector.ISSUE, node, context.getCallLocation(node, true, true), "NO Access");
    }
}