/*
 * Copyright 2025 The ChaosBlade Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.chaosblade.exec.common.model.matcher;

import com.alibaba.chaosblade.exec.common.aop.PredicateResult;
import com.alibaba.chaosblade.exec.common.aop.matcher.busi.BusinessParamPatternEnum;
import com.alibaba.chaosblade.exec.common.constant.ModelConstant;
import com.alibaba.chaosblade.exec.common.util.BusinessParamUtil;
import com.alibaba.chaosblade.exec.common.util.StringUtils;
import java.util.List;

/** @author wufunc@gmail.com */
public class BusinessParamsMatcherSpec extends BasePredicateMatcherSpec {
  @Override
  public String getName() {
    return ModelConstant.BUSINESS_PARAMS;
  }

  @Override
  public String getDesc() {
    return "business parmas";
  }

  @Override
  public boolean noArgs() {
    return false;
  }

  @Override
  public boolean required() {
    return false;
  }

  @Override
  public PredicateResult predicate(MatcherModel matcherModel) {
    String bParam = matcherModel.get(ModelConstant.BUSINESS_PARAMS);
    if (StringUtils.isEmpty(bParam)) {
      return PredicateResult.success();
    }
    BusinessParamUtil.BusinessParamWrapper businessParamWrapper =
        BusinessParamUtil.parseFromJsonStr(bParam);
    List<BusinessParamUtil.BusinessParam> params = businessParamWrapper.getParams();
    if (params == null || params.isEmpty()) {
      return PredicateResult.fail(getName() + " illegal json");
    }
    if (BusinessParamPatternEnum.getPatternMatcher(businessParamWrapper.getPattern()) == null) {
      return PredicateResult.fail(getName() + " unsupported matching pattern");
    }
    return PredicateResult.success();
  }
}
