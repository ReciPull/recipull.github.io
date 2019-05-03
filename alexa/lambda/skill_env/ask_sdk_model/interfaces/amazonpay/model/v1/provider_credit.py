# coding: utf-8

#
# Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
# except in compliance with the License. A copy of the License is located at
#
# http://aws.amazon.com/apache2.0/
#
# or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
# the specific language governing permissions and limitations under the License.
#

import pprint
import re  # noqa: F401
import six
import typing
from enum import Enum


if typing.TYPE_CHECKING:
    from typing import Dict, List, Optional
    from datetime import datetime
    from ask_sdk_model.interfaces.amazonpay.model.v1.price import Price


class ProviderCredit(object):
    """

    :param provider_id: This is required only for Ecommerce provider (Solution provider) use cases.
    :type provider_id: (optional) str
    :param credit: 
    :type credit: (optional) ask_sdk_model.interfaces.amazonpay.model.v1.price.Price

    """
    deserialized_types = {
        'provider_id': 'str',
        'credit': 'ask_sdk_model.interfaces.amazonpay.model.v1.price.Price'
    }  # type: Dict

    attribute_map = {
        'provider_id': 'providerId',
        'credit': 'credit'
    }  # type: Dict

    def __init__(self, provider_id=None, credit=None):
        # type: (Optional[str], Optional[Price]) -> None
        """

        :param provider_id: This is required only for Ecommerce provider (Solution provider) use cases.
        :type provider_id: (optional) str
        :param credit: 
        :type credit: (optional) ask_sdk_model.interfaces.amazonpay.model.v1.price.Price
        """
        self.__discriminator_value = None  # type: str

        self.provider_id = provider_id
        self.credit = credit

    def to_dict(self):
        # type: () -> Dict[str, object]
        """Returns the model properties as a dict"""
        result = {}  # type: Dict

        for attr, _ in six.iteritems(self.deserialized_types):
            value = getattr(self, attr)
            if isinstance(value, list):
                result[attr] = list(map(
                    lambda x: x.to_dict() if hasattr(x, "to_dict") else
                    x.value if isinstance(x, Enum) else x,
                    value
                ))
            elif isinstance(value, Enum):
                result[attr] = value.value
            elif hasattr(value, "to_dict"):
                result[attr] = value.to_dict()
            elif isinstance(value, dict):
                result[attr] = dict(map(
                    lambda item: (item[0], item[1].to_dict())
                    if hasattr(item[1], "to_dict") else
                    (item[0], item[1].value)
                    if isinstance(item[1], Enum) else item,
                    value.items()
                ))
            else:
                result[attr] = value

        return result

    def to_str(self):
        # type: () -> str
        """Returns the string representation of the model"""
        return pprint.pformat(self.to_dict())

    def __repr__(self):
        # type: () -> str
        """For `print` and `pprint`"""
        return self.to_str()

    def __eq__(self, other):
        # type: (object) -> bool
        """Returns true if both objects are equal"""
        if not isinstance(other, ProviderCredit):
            return False

        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        # type: (object) -> bool
        """Returns true if both objects are not equal"""
        return not self == other
