//
//  CHMerchantView.h
//  shenbianapp
//
//  Created by book on 2017/9/3.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CHMerchentModel.h"

typedef void(^SelectedMerchant)(CHMerchentModel *model);

@interface CHMerchantView : UIView
@property(nonatomic,strong)NSArray *merchentList;
@property(nonatomic,copy)SelectedMerchant selectedMerchant;

@end
