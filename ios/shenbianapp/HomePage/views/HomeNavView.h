//
//  HomeNavView.h
//  Miaohi
//
//  Created by 杨绍智 on 17/5/8.
//  Copyright © 2017年 haiqiu. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef void(^GoShoppingCart)();

typedef void(^LocationSearch)();

@interface HomeNavView : UIView

@property(nonatomic,strong)UIButton *locationButton;
@property(nonatomic,strong)NSArray *quikSearchList;
@property(nonatomic,strong)GoShoppingCart goShoppingCart;
@property(nonatomic,strong)LocationSearch locationSearch;
@end
